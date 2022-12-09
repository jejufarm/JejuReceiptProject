package kr.ac.koreatech.jejureceiptproject.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.io.Serializable;

public class SQLiteHelper extends android.database.sqlite.SQLiteOpenHelper implements Serializable {
    private static SQLiteHelper sqLiteHelper;

    public static void setInstance(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        sqLiteHelper = new SQLiteHelper(context, name, factory, version);
    }

    public static SQLiteHelper getInstance() throws Exception {
        if (sqLiteHelper == null)
            throw new Exception("set이 필요합니다.");
        return sqLiteHelper;
    }

    public SQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_query = "CREATE TABLE if not exists `cactus`(" +
                "`uid` INTEGER PRIMARY KEY autoincrement," +
                "`name` varchar(100) NOT NULL DEFAULT ''," +
                "`count` INTEGER NOT NULL," +
                "`price` INTEGER NOT NULL," +
                "`order` INTEGER NOT NULL);";
        db.execSQL(create_query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop_query = "DROP TABLE cactus;";
        db.execSQL(drop_query);

        onCreate(db);
    }
}