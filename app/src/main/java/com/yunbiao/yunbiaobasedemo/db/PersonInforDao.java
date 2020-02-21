package com.yunbiao.yunbiaobasedemo.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.yunbiao.yunbiaobasedemo.ui.bean.PersonInfor;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "PERSON_INFOR".
*/
public class PersonInforDao extends AbstractDao<PersonInfor, Long> {

    public static final String TABLENAME = "PERSON_INFOR";

    /**
     * Properties of entity PersonInfor.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property PerNo = new Property(1, String.class, "perNo", false, "PER_NO");
        public final static Property Name = new Property(2, String.class, "name", false, "NAME");
        public final static Property Sex = new Property(3, String.class, "sex", false, "SEX");
    }


    public PersonInforDao(DaoConfig config) {
        super(config);
    }
    
    public PersonInforDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"PERSON_INFOR\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"PER_NO\" TEXT," + // 1: perNo
                "\"NAME\" TEXT," + // 2: name
                "\"SEX\" TEXT);"); // 3: sex
        // Add Indexes
        db.execSQL("CREATE UNIQUE INDEX " + constraint + "IDX_PERSON_INFOR_PER_NO ON \"PERSON_INFOR\"" +
                " (\"PER_NO\" ASC);");
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"PERSON_INFOR\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, PersonInfor entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String perNo = entity.getPerNo();
        if (perNo != null) {
            stmt.bindString(2, perNo);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(3, name);
        }
 
        String sex = entity.getSex();
        if (sex != null) {
            stmt.bindString(4, sex);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, PersonInfor entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String perNo = entity.getPerNo();
        if (perNo != null) {
            stmt.bindString(2, perNo);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(3, name);
        }
 
        String sex = entity.getSex();
        if (sex != null) {
            stmt.bindString(4, sex);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public PersonInfor readEntity(Cursor cursor, int offset) {
        PersonInfor entity = new PersonInfor( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // perNo
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // name
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3) // sex
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, PersonInfor entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setPerNo(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setSex(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(PersonInfor entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(PersonInfor entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(PersonInfor entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
