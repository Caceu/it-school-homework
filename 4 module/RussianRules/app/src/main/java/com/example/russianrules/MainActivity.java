package com.example.russianrules;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    SimpleAdapter adapter;
    OpenHelper dbHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new OpenHelper(this);
        db = dbHelper.getWritableDatabase();
        LinkedList<Rule> listRules = new LinkedList<>();
        listRules.add(new Rule("-Н-, -НН-", "Причастия", "Но поскольку полные страдательные причастия иногда очень похожи на прилагательные, чтобы избежать ошибок, полезно помнить четыре условия, при которых пишется две буквы Н:\n" +
                "\n" +
                "1) причастие имеет приставку ( кроме НЕ-), например, ВЫСУШЕННЫЕ ГРИБЫ;\n" +
                "\n" +
                "2) причастие образовалось от глагола совершенного вида, например, РЕШЕННАЯ ЗАДАЧА - от глагола РЕШИТЬ (что сделать? – соверш. вид);\n" +
                "\n" +
                "3) причастие имеет зависимые слова, например, ВЫСУШЕННЫЕ (где?) В ПЕЧКЕ ГРИБЫ;\n" +
                "\n" +
                "4) Причастие образовано от глагола с помощью суффиксов –ОВА- или -ЕВА-, например, ВЗВОЛНОВАННОЕ ЛИЦО.\n" +
                "\n" +
                "Если слово не имеет ни одного из этих признаков, то оно пишется с одним Н. "));
        listRules.add(new Rule("-Н-, -НН-", "Прилагательные", "Одна буква Н пишется в суффиксах -ан/-ян, -ын/-ин отыменных прилагательных: ледяной (< лёд), песчаный (< песок), серебряный (< серебро), комариный (< комар). НН пишутся:\n" +
                "\n" +
                " \n" +
                "\n" +
                "а) в отыменных прилагательных, образованных от основ на -н, -мя при помощи суффикса -н: каменный (< камень), пламенный (< пламя), осенний (< осень), весенний (< весна);\n" +
                "\n" +
                " \n" +
                "\n" +
                "б) в суффиксах -енн, -онн отыменных прилагательных: обеденный (< обед), революционный (< революция). Прилагательные с суффиксом -енн могут выражать субъективную оценку (большую меру признака): здоровенный, тяжеленный, высоченный (ср. здоровый, тяжёлый, высокий).\n" +
                "\n" +
                "  \n" +
                "\n" +
                "Исключение: ветреный, однако в приставочных образованиях пишется -нн: подветренный, безветренный и т. д.\n" +
                "\n" +
                " \n" +
                "\n" +
                "В кратких прилагательных пишется столько же н, сколько их было в полной форме, например: речь изысканна, девушка ветрена."));
        
        

        listView = (ListView) findViewById(R.id.list_view);

        List<HashMap<String, Object>> dataRules = new ArrayList<>();

        for (int i = 0; i < listRules.size(); i++) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("name", listRules.get(i).getName());
            map.put("part", listRules.get(i).getPart());
            map.put("description", listRules.get(i).getDescription());
            dataRules.add(map);

            ContentValues contents = new ContentValues();
            contents.put("name", listRules.get(i).getName());
            contents.put("part", listRules.get(i).getPart());
            contents.put("description", listRules.get(i).getDescription());
        }

        String[] from = new String[]{"name", "part", "description"};
        int[] to = new int[]{R.id.name, R.id.part, R.id.description};

        adapter = new SimpleAdapter(this, dataRules, R.layout.list_item, from, to);
        listView.setAdapter(adapter);
    }
}

class Rule {
    String name, part, description;

    /**
     * @param name        Name of a rule
     * @param part        Part of speech for the rule
     * @param description Text of the rule
     */
    public Rule(String name, String part, String description) {
        this.name = name;
        this.part = part;
        this.description = description;
    }

    /**
     * @return Rule to string
     */
    @Override
    public String toString() {
        return "RussianRule{" +
                "name='" + name + '\'' +
                ", part='" + part + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public String getPart() {
        return part;
    }

    public String getDescription() {
        return description;
    }
}