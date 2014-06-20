package com.example.pt_assistant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class PT_SQLiteHelper extends SQLiteOpenHelper {
	// Database Version
	private static final int DATABASE_VERSION = 2;
	// Database Name
	private static final String DATABASE_NAME = "TherapistDB";

	public PT_SQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// SQL statement to create book table
		String CREATE_PATIENT_TABLE = "CREATE TABLE patient ( "
				+ "p_id INTEGER," + "name TEXT," + "dob  TEXT,"
				+ "age  INTEGER," + "sex  INTEGER," + "injury_code INTEGER )";

		// create books table
		db.execSQL(CREATE_PATIENT_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older books table if existed
		db.execSQL("DROP TABLE IF EXISTS patient");

		// create fresh books table
		this.onCreate(db);
	}

	// ////////////////////////////////////
	/**
	 * CRUD operations (create "add", read "get", update, delete) book + get all
	 * books + delete all books
	 */

	// Books table name
	private static final String TABLE_PATIENT = "patient";

	// Books Table Columns names
	private static final String P_ID = "p_id";
	private static final String NAME = "name";
	private static final String AGE = "age";
	private static final String INJURY_CODE = "injury_code";
	private static final String DOB = "dob";
	private static final String SEX = "sex";
	private static final String[] COLUMNS = { P_ID, NAME, DOB, AGE, SEX,
			INJURY_CODE };

	public long addPatient(Patient patient) {
		Log.d("addPatient", patient.toString());
		// 1. get reference to writable DB
		SQLiteDatabase db = this.getWritableDatabase();

		// 2. create ContentValues to add key "column"/value
		ContentValues values = new ContentValues();
		values.put(P_ID, patient.getPatientID());
		values.put(NAME, patient.getName());
		values.put(DOB, patient.DOB);
		values.put(AGE, patient.age);
		values.put(SEX, patient.sex);
		values.put(INJURY_CODE, patient.getInjury());

		// 3. insert
		long temp = db.insert(TABLE_PATIENT, // table
				null, // nullColumnHack
				values); // key/value -> keys = column names/ values = column
							// values

		// 4. close
		db.close();
		return temp;
	}

	public Patient getPatient(int p_id) {

		// 1. get reference to readable DB
		SQLiteDatabase db = this.getReadableDatabase();

		// 2. build query
		Cursor cursor = db.query(TABLE_PATIENT, // a. table
				COLUMNS, // b. column names
				" p_id = ?", // c. selections
				new String[] { String.valueOf(p_id) }, // d. selections args
				null, // e. group by
				null, // f. having
				null, // g. order by
				null); // h. limit

		// 3. if we got results get the first one
		if (cursor != null)
			cursor.moveToFirst();

		// 4. build patient object
		Patient patient = new Patient();
		patient.setPatientID(Integer.parseInt(cursor.getString(0)));
		patient.setName(cursor.getString(1));
		patient.DOB = cursor.getString(2);
		patient.age = Integer.parseInt(cursor.getString(3));
		patient.sex = Integer.parseInt(cursor.getString(4));
		patient.setInjury(Integer.parseInt(cursor.getString(5)));

		Log.d("get(" + p_id + ")", patient.toString());

		// 5. return patient
		return patient;
	}

	// Get All PATIENTS
	// public List<Book> getAllBooks() {
	// List<Book> books = new LinkedList<Book>();

	// // 1. build the query
	// String query = "SELECT  * FROM " + TABLE_BOOKS;

	// // 2. get reference to writable DB
	// SQLiteDatabase db = this.getWritableDatabase();
	// Cursor cursor = db.rawQuery(query, null);

	// // 3. go over each row, build book and add it to list
	// Book book = null;
	// if (cursor.moveToFirst()) {
	// do {
	// book = new Book();
	// book.setId(Integer.parseInt(cursor.getString(0)));
	// book.setTitle(cursor.getString(1));
	// book.setAuthor(cursor.getString(2));

	// // Add book to books
	// books.add(book);
	// } while (cursor.moveToNext());
	// }

	// Log.d("getAllBooks()", books.toString());

	// // return books
	// return books;
	// }
	// Updating single book
	public int updatePatient(Patient patient) {

		// // 1. get reference to writable DB
		SQLiteDatabase db = this.getWritableDatabase();

		// // 2. create ContentValues to add key "column"/value
		ContentValues values = new ContentValues();
		values.put(NAME, patient.getName()); // get title
		values.put(AGE, String.valueOf(patient.age)); // get author
		values.put(INJURY_CODE, String.valueOf(patient.getInjury())); // get
																		// author
		values.put(DOB, patient.DOB); // get author
		values.put(AGE, String.valueOf(patient.age)); // get author
		values.put(SEX, String.valueOf(patient.age));

		// // 3. updating row
		int i = db.update(TABLE_PATIENT, // table
				values, // column/value
				P_ID + " = ?", // selections
				new String[] { String.valueOf(patient.getPatientID()) }

		); // selection args

		// 4. close
		db.close();

		return i;

	}

	// Deleting single book
	// public void deleteBook(Book book) {

	// // 1. get reference to writable DB
	// SQLiteDatabase db = this.getWritableDatabase();

	// // 2. delete
	// db.delete(TABLE_BOOKS,
	// P_ID+" = ?",
	// new String[] { String.valueOf(book.getId()) });

	// // 3. close
	// db.close();

	// Log.d("deleteBook", book.toString());

	// }

	// /////////////////////////////////////
}
