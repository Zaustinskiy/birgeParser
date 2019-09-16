import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {
        final String url = "https://cash.rbc.ru/cash/?currency=3&city=1&deal=buy&amount=100";
        String value = "";
        String bankName = "";
        String phone = "";
        Double valueInt;
        String inf = "";
        Double minValue = 1000d;
        Map<String, Double> hashMap = new HashMap<String, Double>();
        try{
            final Document doc = Jsoup.connect(url).get();
            Elements listEl = doc.select("div.quote__office__content.js-office-content");
            for (Element row : listEl.select("div.quote__office__one.js-one-office"))
            {
                value = row.select("div.quote__office__cell.quote__office__one__rate.quote__mode_list_view").text();
                valueInt = Double.parseDouble(value);
                Elements row1 = row.select("div.quote__office__cell");
                bankName = row1.select("a.quote__office__one__name").text();
                phone = row1.select("div.quote__office__one__phone").text();
                inf = " в " + bankName + phone;
                hashMap.put(inf, valueInt);
            }


            for (Double i : hashMap.values())
            {
                if (i < minValue)
                    minValue = i;
                    inf = hashMap.keySet().toString();
            }
            System.out.println("Min value is: " + minValue + "  " + inf);

        }catch (Exception ex){
            ex.printStackTrace();
        }

    }
}
