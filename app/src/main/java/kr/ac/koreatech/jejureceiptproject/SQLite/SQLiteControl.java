package kr.ac.koreatech.jejureceiptproject.SQLite;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.ac.koreatech.jejureceiptproject.domain.CactusDTO;

public class SQLiteControl implements Serializable {
    SQLiteHelper helper;
    SQLiteDatabase sqlite;

    private static SQLiteControl instance;

    public static void setInstance(SQLiteHelper helper) {
        instance = new SQLiteControl(helper);
    }

    public static SQLiteControl getInstance() throws Exception {
        if (instance == null)
            throw new Exception("set이 필요합니다.");
        return instance;
    }

    public SQLiteControl(SQLiteHelper _helper) {
        this.helper = _helper;
    }

    public List<Map<String, String>> GetData(String sql) {
        List<Map<String, String>> temp = new ArrayList<>();
        sqlite = helper.getReadableDatabase();
        Cursor c = sqlite.rawQuery(sql, null);
        if (c.moveToFirst()) {
            do {
                for (int i = 0; i < c.getColumnCount(); i++) {
                    Map<String, String> data = new HashMap<>();
                    data.put(c.getColumnName(i), c.getString(i));
                    temp.add(data);
                }
//                temp.add(new CactusDTO(c.getLong(0), c.getString(1), c.getInt(2)));
            } while (c.moveToNext());
        }
        c.close();
        return temp;
    }

    public List<CactusDTO> getCacutsList() {
        List<CactusDTO> temp = new ArrayList<>();
        sqlite = helper.getReadableDatabase();
        Cursor c = sqlite.rawQuery("SELECT * FROM cactus ORDER BY `order` ASC;", null);
        if (c.moveToFirst()) {
            do {
                temp.add(new CactusDTO(c.getInt(0), c.getString(1), c.getInt(2), c.getInt(3), c.getInt(4)));
            } while (c.moveToNext());
        }
        c.close();
        return temp;
    }

    public boolean insert(int order, String name, Integer count, Integer price) {
        try {
            if (order < 0) {
                List<Map<String, String>> temp = GetData("SELECT count(*) FROM cactus;");
                order = Integer.parseInt(temp.get(0).get("count(*)"));
            }


            return executeSQL("INSERT INTO cactus(`name`, `count` , `price`, `order`) VALUES " +
                    "(\"" + name + "\",'" + count + "', '" + price + "'," + order + ");");

        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public boolean delete(CactusDTO cactus) {
        try {
            if (executeSQL("DELETE FROM cactus WHERE uid = " + cactus.getUid())) {
                executeSQL("UPDATE cactus SET `order` = `order` - 1 WHERE  `order` > " + cactus.getOrder());
                return true;

            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return false;
    }

    public boolean update(CactusDTO cactus) {
        try {

            return executeSQL("UPDATE cactus SET name = '" + cactus.getName() + "'," +
                    "price = " + cactus.getPrice() + ", count = " + cactus.getCount() + " WHERE uid = " + cactus.getUid() + ";");
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
//        return false;
    }

    public boolean swipe(CactusDTO from_cactus, CactusDTO to_cactus) {
        try {
            System.out.println(from_cactus.getOrder() + " " + to_cactus.getOrder());
            if (executeSQL("UPDATE cactus SET `order` = " + to_cactus.getOrder() +
                    " WHERE uid = " + to_cactus.getUid() + ";") &&
                    executeSQL("UPDATE cactus SET `order` = " + from_cactus.getOrder() +
                            " WHERE uid = " + from_cactus.getUid() + ";")) {
                return true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return false;
    }

    public boolean executeSQL(String sql) {
        try {
            sqlite = helper.getWritableDatabase();
            sqlite.execSQL(sql);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
