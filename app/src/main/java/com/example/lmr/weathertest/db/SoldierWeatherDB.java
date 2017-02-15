package com.example.lmr.weathertest.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.lmr.weathertest.model.City;
import com.example.lmr.weathertest.model.District;
import com.example.lmr.weathertest.model.Province;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LMR on 2017/2/15.
 */

public class SoldierWeatherDB {

    /**
     * 数据库名
     */
    private static final String DB_NAME = "soldier_weather";

    /**
     * 数据库版本
     */
    private static final int VERSION = 1;

    private static SoldierWeatherDB soldierWeatherDB;

    private SQLiteDatabase db;

    /**
     * 将构造方法私有化
     */
    private SoldierWeatherDB(Context context) {
        SoldierWeatherOpenHelper dbHelper = new SoldierWeatherOpenHelper(context, DB_NAME, null, VERSION);
        db = dbHelper.getWritableDatabase();
    }

    /**
     * 获取SoldierWeatherDB的实例
     */
    public synchronized static SoldierWeatherDB getInstance(Context context) {
        if (soldierWeatherDB == null) {
            soldierWeatherDB = new SoldierWeatherDB(context);
        }
        return soldierWeatherDB;
    }

    /**
     * 将Province实例存储到数据库
     */
    public synchronized void saveProvince(Province province) {
        ContentValues values = new ContentValues();
        values.put("province_name", province.getProvinceName());
        db.insert("Province", null, values);
    }

    /**
     * 从数据库读取全国所有的省份信息
     */
    public List<Province> loadProvinces() {
        List<Province> list = new ArrayList<>();
        Cursor cursor = db.query("Province", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Province province = new Province();
                province.setId(cursor.getInt(cursor.getColumnIndex("id")));
                province.setProvinceName(cursor.getString(cursor.getColumnIndex("province_name")));
                list.add(province);
            } while (cursor.moveToNext());
        }
        if (cursor != null) {
            cursor.close();
        }
        return list;
    }

    /**
     * 将City实例存储到数据库
     */
    public synchronized void saveCity(City city) {
        ContentValues values = new ContentValues();
        values.put("city_name", city.getCityName());
        values.put("province_id", city.getProvinceId());
        db.insert("City", null, values);
    }

    /**
     * 从数据库读取某省下所有的城市信息
     */
    public List<City> loadCities(int provinceId) {
        List<City> list = new ArrayList<>();
        Cursor cursor = db.query("City", null, "province_id = ?", new String[]{String.valueOf(provinceId)},
                null, null, null);
        if (cursor.moveToFirst()) {
            do {
                City city = new City();
                city.setId(cursor.getInt(cursor.getColumnIndex("id")));
                city.setCityName(cursor.getString(cursor.getColumnIndex("city_name")));
                city.setProvinceId(cursor.getInt(cursor.getColumnIndex("province_id")));
                list.add(city);
            } while (cursor.moveToNext());
        }
        if (cursor != null) {
            cursor.close();
        }
        return list;
    }

    /**
     * 将County实例存储到数据库
     */
    public synchronized void saveDistrict(District district) {
        ContentValues values = new ContentValues();
        values.put("district_name", district.getDistrictName());
        values.put("city_id", district.getCityId());
        db.insert("District", null, values);
    }

    /**
     * 从数据库读取某城市下所有的县信息
     */
    public List<District> loadDistricts(int cityId) {
        List<District> lists = new ArrayList<>();
        Cursor cursor = db.query("District", null, "city_id = ?", new String[]{String.valueOf(cityId)},
                null, null, null);
        if (cursor.moveToFirst()) {
            do {
                District district = new District();
                district.setId(cursor.getInt(cursor.getColumnIndex("id")));
                district.setDistrictName(cursor.getString(cursor.getColumnIndex("district_name")));
                district.setCityId(cursor.getInt(cursor.getColumnIndex("city_id")));
                lists.add(district);
            } while (cursor.moveToNext());
        }
        if (cursor != null) {
            cursor.close();
        }
        return lists;
    }
}
