package app.vanillajavaapi.utils;

public class JsonBuilder {

    public static String criarJson(String... parametros) {

        StringBuilder json = new StringBuilder("{");

        for (int i = 0; i < parametros.length; i += 2) {
            String chave = parametros[i];
            String valor = parametros[i + 1];

            json.append("\"").append(chave).append("\"")
                    .append(":")
                    .append("\"").append(valor).append("\"");

            if (i + 2 < parametros.length) {
                json.append(",");
            }
        }

        json.append("}");

        return json.toString();
    }
}
