package services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.UsuarioModel;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.Map;
import java.util.Set;

import static io.restassured.RestAssured.given;

public class CadastroUsuariosService {
    final UsuarioModel usuarioModel = new UsuarioModel();
    public final Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();
    String baseUrl = "https://cadastro-usuarios-smartwastmanagement-hxawh3drg3c5exgf.canadacentral-01.azurewebsites.net/usuarios";
    public Response response;
    String idUsuario;
    JSONObject jsonSchema;
    String schemasPath = "src/test/resources/schemas/";

    private final ObjectMapper mapper = new ObjectMapper();


    public void setFieldsUsuario(String field, String value) {
        switch (field) {
            case "idUsuario" -> usuarioModel.setIdUsuario(value);
            case "nmUsuario" -> usuarioModel.setNmUsuario(value);
            case "dsEmail" -> usuarioModel.setDsEmail(value);
            case "nrCpf" -> usuarioModel.setNrCpf(value);
            case "dtNasc" -> usuarioModel.setDtNasc(value);
            default -> throw new IllegalStateException("Campo inesperado: " + field);
        }
    }

    public void createUsuario(String endpoint) {
        String url = baseUrl + endpoint;
        String bodyToSend = gson.toJson(usuarioModel);

        response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(bodyToSend)
                .when()
                .post(url)
                .then()
                .extract()
                .response();
    }

    public void retrieveIdUsuario() {
        idUsuario = String.valueOf(gson.fromJson(response.jsonPath().prettify(), UsuarioModel.class).getIdUsuario());
    }

    public void deleteUsuario(String endpoint) {
        String url = String.format("%s%s/%s", baseUrl, endpoint, idUsuario);
        response = given()
                .when()
                .delete(url)
                .then()
                .extract()
                .response();
    }
    public void setContract(String contract) throws IOException, JSONException {
        switch (contract) {
            case "Cadastro bem-sucedido de entrega" -> jsonSchema = loadJsonFromFile(schemasPath + "cadastro-bem-sucedido-de-entrega.json");
            case "Cadastro bem-sucedido de usuario" -> jsonSchema = loadJsonFromFile(schemasPath + "cadastro-bem-sucedido-de-usuario.json");
            default -> throw new IllegalStateException("Unexpected contract: " + contract);
        }
    }


    public JSONObject loadJsonFromFile(String filePath) throws IOException, JSONException {
        String content;
        try (InputStream inputStream = Files.newInputStream(Paths.get(filePath))) {
            content = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        }
        return new JSONObject(new JSONTokener(content));
    }
    public Set<ValidationMessage> validateResponseAgainstSchema() throws IOException, JSONException {
        JSONObject jsonResponse = new JSONObject(response.getBody().asString());
        JsonSchemaFactory schemaFactory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
        JsonSchema schema = schemaFactory.getSchema(jsonSchema.toString());
        JsonNode jsonResponseNode = mapper.readTree(jsonResponse.toString());
        Set<ValidationMessage> schemaValidationErrors = schema.validate(jsonResponseNode);
        return schemaValidationErrors;
    }



}
