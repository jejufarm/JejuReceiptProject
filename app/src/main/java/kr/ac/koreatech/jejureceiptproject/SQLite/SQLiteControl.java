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
                System.out.println("dd");
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
                temp.add(new CactusDTO(c.getInt(0), c.getString(1), c.getInt(2), c.getInt(3)));
            } while (c.moveToNext());
        }
        c.close();
        return temp;
    }

    public boolean insert(int order, String name, Integer price) {
        try {
            if (order < 0) {
                List<Map<String, String>> temp = GetData("SELECT count(*) FROM cactus;");
                order = Integer.parseInt(temp.get(0).get("count(*)"));
            }


            return executeSQL("INSERT INTO cactus(`name`, `price`, `order`) VALUES " +
                    "(\"" + name + "\",'" + price + "'," + order + ");");

        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public boolean delete(int uid) {
//        try {
//            if (ExecuteSQL("DELETE FROM cactuslist WHERE uid = " + uid)) {
//                ExecuteSQL("UPDATE cactus_list set uid = uid - 1 WHERE  uid > " + uid);
//            }
//            return true;
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            throw ex;
//        }
        return false;
    }

    public boolean update(CactusDTO cactus) {
//        try {
//            if (cactus.getUid() < 0)
//                return false;
//            ExecuteSQL("SELECT * FROM CACTUSLIST where cactus_uid = " + cactus.getUid());
//            ArrayList<CactusDTO> temp = (ArrayList<CactusDTO>) GetData("SELECT * FROM CACTUSLIST where cactus_uid = " + cactus.getUid());
//            if (temp.size() > 0) {
//                ExecuteSQL("UPDATE CACTUSLIST SET cactus_name = '" + cactus.getName() + "'," +
//                        "cactus_price = " + cactus.getPrice() + " WHERE cactus_uid = " + cactus.getUid() + ";");
//            }
//            return true;
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            throw ex;
//        }
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
