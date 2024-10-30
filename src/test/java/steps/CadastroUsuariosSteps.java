package steps;

import com.networknt.schema.ValidationMessage;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Quando;
import io.cucumber.java.pt.Então;
import model.ErrorMessageModel;
import org.json.JSONException;
import org.junit.Assert;
import services.CadastroUsuariosService;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CadastroUsuariosSteps {

    CadastroUsuariosService cadastroUsuariosService = new CadastroUsuariosService();

    @Dado("que eu tenha os seguintes dados do usuário:")
    public void queEuTenhaOsSeguintesDadosDoUsuario(List<Map<String, String>> rows) {
        for (Map<String, String> columns : rows) {
            cadastroUsuariosService.setFieldsUsuario(columns.get("campo"), columns.get("valor"));
        }
    }

    @Quando("eu enviar a requisisição para o endpoint {string} para cadastro de usuário")
    public void euEnviarARequisicaoParaOEndpointParaCadastroDeUsuario(String endPoint) {
        cadastroUsuariosService.createUsuario(endPoint);
    }

    @Então("o status code deve retornar {int}")
    public void oStatusCodeDeveRetornar(int statusCode) {
        Assert.assertEquals(statusCode, cadastroUsuariosService.response.statusCode());
    }

    @E("o corpo de resposta de erro da api deve conter status {int}, erro {string} e path {string}")
    public void oCorpoDeRespostaDeErroDaApiDeveConterStatusErroEPath(int status, String error, String path) {
        ErrorMessageModel errorMessageModel = cadastroUsuariosService.gson.fromJson(
                cadastroUsuariosService.response.jsonPath().prettify(), ErrorMessageModel.class);

        Assert.assertEquals(status, errorMessageModel.getStatus());
        Assert.assertEquals(error, errorMessageModel.getError());
        Assert.assertEquals(path, errorMessageModel.getPath());
    }

    @Dado("que eu recupere o ID do usuario criado no contexto")
    public void queEuRecupereOIDDoUsuarioCriadoNoContexto() {
        cadastroUsuariosService.retrieveIdUsuario();
    }

    @Quando("eu enviar a requisisição com o ID para o endpoint {string} de delecao de usuario")
    public void euEnviarARequisisiçãoComOIDParaOEndpointDeDelecaoDeUsuario(String endPoint) {
        cadastroUsuariosService.deleteUsuario(endPoint);
    }

    @E("o arquivo de contrato esperado é o {string}")
    public void oArquivoDeContratoEsperadoÉO(String contract) throws JSONException, IOException {
        cadastroUsuariosService.setContract(contract);
    }

    @Então("a resposta da requisição deve estar em conformidade com o contrato selecionado")
    public void aRespostaDaRequisiçãoDeveEstarEmConformidadeComOContratoSelecionado() throws IOException, JSONException {
        Set<ValidationMessage> validateResponse = cadastroUsuariosService.validateResponseAgainstSchema();
        Assert.assertTrue("O contrato está inválido. Erros encontrados: " + validateResponse, validateResponse.isEmpty());
    }
}
