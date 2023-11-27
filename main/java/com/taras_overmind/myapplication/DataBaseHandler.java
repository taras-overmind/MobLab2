package com.taras_overmind.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHandler extends SQLiteOpenHelper {
    private static final String CREATE_QUERY = "CREATE TABLE cars (" +
            "id SERIAL PRIMARY KEY" +
            ", brand VARCHAR(255)" +
            ",body_type VARCHAR(255)" +
            ",color VARCHAR(255)" +
            ",engine_capacity INT" +
            ",price INT" +
            ");";

    public DataBaseHandler(@Nullable Context context) {
        super(context, "cars", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS cars");
    }
    public List<CarModel> getAll(){
        List<CarModel> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM CARS";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()){
            do{
                int carId = cursor.getInt(0);
                String brand = cursor.getString(1);
                String  bodyType = cursor.getString(2);
                String  color = cursor.getString(3);
                double  engineCapacity = cursor.getDouble(4);
                double  price = cursor.getDouble(5);

                CarModel newCar = new CarModel(carId, brand, bodyType, color, engineCapacity, price);
                returnList.add(newCar);
            }while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return returnList;
    }

    public boolean addOne(CarModel carModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("brand", carModel.getBrand());
        cv.put("body_type", carModel.getBodyType());
        cv.put("color", carModel.getColor());
        cv.put("engine_capacity", carModel.getEngineCapacity());
        cv.put("price", carModel.getPrice());

        long insert = db.insert("cars", null, cv);
        if(insert == -1){
            return false;
        }
        else{
            return true;
        }
    }
    public double getAverageEngineCapacity() {
        String queryString = "SELECT AVG(ENGINE_CAPACITY) FROM CARS";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            return cursor.getDouble(0);
        }
        else
            return 0;
    }
    public List<CarModel>  getSelectedCars(){
        List<CarModel> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM CARS WHERE body_type='Universal' AND color='Red'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()){
            do{
                int carId = cursor.getInt(0);
                String brand = cursor.getString(1);
                String  bodyType = cursor.getString(2);
                String  color = cursor.getString(3);
                double  engineCapacity = cursor.getDouble(4);
                double  price = cursor.getDouble(5);

                CarModel newCar = new CarModel(carId, brand, bodyType, color, engineCapacity, price);
                returnList.add(newCar);
            }while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return returnList;
    }
    public void deleteOne(String brand){
        try(SQLiteDatabase db = this.getWritableDatabase()){

            db.delete("cars", "brand=? ", new String[]{brand});
        }

    }
}
