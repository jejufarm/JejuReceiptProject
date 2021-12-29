package kr.ac.koreatech.jejureceiptproject.SQLite;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

import kr.ac.koreatech.jejureceiptproject.domain.CactusDTO;

public class SQLiteControl implements Serializable {
    SQLiteHelper helper;
    SQLiteDatabase sqlite;

    public SQLiteControl(SQLiteHelper _helper) {
        this.helper = _helper;
    }

    public Map<String, Object> GetData(String sql) {
        ArrayList<CactusDTO> temp = new ArrayList<>();
        sqlite = helper.getReadableDatabase();
        Cursor c = sqlite.rawQuery(sql, null);
        if (c.moveToFirst()) {
            do {
                temp.add(new CactusDTO(c.getLong(0), c.getString(1), c.getInt(2)));
            } while (c.moveToNext());
        }
        c.close();
        return null;
    }

    public boolean Insert(int order, CactusDTO cactus) {
        try {
            if (order < 0) {
                ExecuteSQL("SELECT count(*) FROM cactus;");
                Map<String, Object> temp = GetData("SELECT * FROM CACTUSLIST where cactus_uid = " + cactus.getUid());
                System.out.println("dd");
            }


            ExecuteSQL("INSERT INTO cactus(uid, name, price) VALUES " +
                    "(" + cactus.getUid() + ",'" + cactus.getName() + "'," + cactus.getPrice() + ");");

            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public boolean Delete(int uid) {
        try {
            if (ExecuteSQL("DELETE FROM cactuslist WHERE uid = " + uid)) {
                ExecuteSQL("UPDATE cactus_list set uid = uid - 1 WHERE  uid > " + uid);
            }
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public boolean Update(CactusDTO cactus) {
        try {
            if (cactus.getUid() < 0)
                return false;
            ExecuteSQL("SELECT * FROM CACTUSLIST where cactus_uid = " + cactus.getUid());
            ArrayList<CactusDTO> temp = (ArrayList<CactusDTO>) GetData("SELECT * FROM CACTUSLIST where cactus_uid = " + cactus.getUid());
            if (temp.size() > 0) {
                ExecuteSQL("UPDATE CACTUSLIST SET cactus_name = '" + cactus.getName() + "'," +
                        "cactus_price = " + cactus.getPrice() + " WHERE cactus_uid = " + cactus.getUid() + ";");
            }
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public int GetMaxUid() {
        try {
            sqlite = helper.getReadableDatabase();
            Cursor c = sqlite.rawQuery("SELECT MAX(cactus_uid) FROM CACTUSLIST", null);
            int temp = 0;
            if (c.moveToFirst()) {
                temp = c.getInt(0);
            } else {
                throw new Exception();
            }
            c.close();
            return temp + 1;
        } catch (Exception ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    public boolean ExecuteSQL(String sql) {
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
