/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqldatabaseapp;

import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import java.util.ArrayList;
import java.util.Iterator;
import org.bson.Document;
import org.jcp.xml.dsig.internal.dom.Utils;

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
        if (!isValid(name,username, password)) {
            return false;
        }

        if (!collectionExists()) {
            createCollection();
            getCollection();
            insert();
            System.out.println("Success!");
            getUsers();
            return true;
        }
        System.out.println("username exists!");
        getCollection();
        getUsers();
        return false;

    }

    public boolean isValid(String name, String username, String password) {
        if (name.isEmpty() || username.isEmpty() || password.isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean isValid(String username, String password) {
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
                //.append("id", getID())
                .append("name", name)
                .append("username", username)
                .append("password", password);
        collection.insertOne(document);
    }

    public boolean collectionExists() {
        boolean usernameExists = database.listCollectionNames()
                .into(new ArrayList<String>()).contains(username);
        return usernameExists;
    }

    public void getUsers() {
        // Retrieving a collection
        ArrayList<String> allCollections = new ArrayList<>();
        database.listCollectionNames().into(allCollections);

        for (Iterator<String> item = allCollections.iterator(); item.hasNext();) {
            MongoCollection<Document> myColl = database.getCollection(item.next());

            // Getting the iterable object 
            FindIterable<Document> iterDoc = myColl.find();
            int i = 1;

            // Getting the iterator 
            Iterator it = iterDoc.iterator();

            while (it.hasNext()) {
                System.out.println(it.next());
                i++;
            }
        }
    }

}
