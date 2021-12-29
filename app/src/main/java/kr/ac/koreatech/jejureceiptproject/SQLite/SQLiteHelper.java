package kr.ac.koreatech.jejureceiptproject.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.io.Serializable;

public class SQLiteHelper extends android.database.sqlite.SQLiteOpenHelper implements Serializable {

    public SQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_query = "CREATE TABLE if not exists `cactus`(" +
                "`uid` bigint(20) unsigned NOT NULL AUTO_INCREMENT," +
                "`name` varchar(100) NOT NULL DEFAULT ''," +
                "`price` int(11) unsigned NOT NULL," +
                "`order` int(11) unsigned NOT NULL," +
                "PRIMARY KEY (`uid`)," +
                "UNIQUE KEY `order` (`order`)," +
                "KEY `order_2` (`order`));";
        db.execSQL(create_query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop_query = "DROP TABLE cactus;";
        db.execSQL(drop_query);

        onCreate(db);
    }
}