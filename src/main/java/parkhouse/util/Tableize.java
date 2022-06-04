package parkhouse.util;

public class Tableize {

    public static String table(String[] headers, String[][] rows) {
        StringBuilder sb = new StringBuilder("<table>");
        for (String h : headers) {
            sb.append("<th>").append(h).append("</th>");
        }
        for (String[] row : rows) {
            sb.append("<tr>");
            for (String r : row) {
                sb.append("<td>").append(r).append("</td>");
            }
            sb.append("</tr>");
        }
        return sb.toString();
    }
}
