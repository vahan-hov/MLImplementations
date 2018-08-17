/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqldatabaseapp;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import javax.management.Query;
import org.bson.Document;

public class MongoDBManager {

    MongoCollection<org.bson.Document> collection;
    MongoDatabase database;
    String username;
    String name;
    String password;

    public MongoDBManager(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;

    }
    public MongoDBManager(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public boolean mainFunc() {
        createMongoDB();
        if (!isValid()) {
            return false;
        }
        if (!collectionExists(username)) {
            createCollection();
            getCollection();
            insert();
            System.out.println("Success!");
            return true;
        }
        System.out.println("username exists!");
        getCollection();
        getUsers();
        return false;
    }

    public boolean isValid() {
        if (name.isEmpty() || username.isEmpty() || password.isEmpty()) {
            return false;
        }
        return true;
    }
    
    public boolean isValidLogin() {
        if (username.isEmpty() || password.isEmpty()) {
            return false;
        }
        return true;
    }
    
    public void createMongoDB() {
        // Creating a Mongo client 
        MongoClient mongo = new MongoClient("localhost", 27017);

        // Creating Credentials 
        MongoCredential credential;
        credential = MongoCredential.createCredential(username, "myDb",
                "password".toCharArray());
        System.out.println("Connected to the database successfully");

        // Accessing the database 
        database = mongo.getDatabase("myDb");
        System.out.println("Credentials ::" + credential);
    }

    public void createCollection() {
        //Creating a collection 
        database.createCollection(username);
        System.out.println("Collection created successfully");
    }

    public void getCollection() {
        collection = database.getCollection(username);
        System.out.println("Collection " + username + " selected successfully");
    }

    public void insert() {
        Document document = new Document()
                .append("id", getID())
                .append("name", name)
                .append("username", username)
                .append("password", password);
        collection.insertOne(document);
    }

    public static Object getID() {
        final String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        return timeStamp;
    }

    public boolean collectionExists(final String collectionName) {
        boolean collectionExists = database.listCollectionNames()
    .into(new ArrayList<String>()).contains(username);
        return collectionExists;
    }

    public void getUsers() {
        // Getting the iterable object 
        FindIterable<Document> iterDoc = collection.find();
        int i = 1;

        // Getting the iterator 
        Iterator it = iterDoc.iterator();

        while (it.hasNext()) {
            System.out.println(it.next());
            i++;
        }
    }
}
