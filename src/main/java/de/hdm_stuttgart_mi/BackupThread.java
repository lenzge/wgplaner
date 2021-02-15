package de.hdm_stuttgart_mi;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BackupThread implements Runnable {
    private File file = new File("src/main/resources/JSON/roommates.json");
    private File backupFile = new File("src/main/resources/JSON/backup.json");
    private static final Logger log = LogManager.getLogger(BackupThread.class);
    @Override
    public void run() {
        JSONParser parser = new JSONParser();
        while(true){
            try {
                String content = new String(Files.readAllBytes(Paths.get(file.toURI())), "UTF-8");
                JSONObject json = (JSONObject) parser.parse(content);

                JSONArray jsonArray = (JSONArray) json.get("roommates");

                try (FileWriter writer = new FileWriter(backupFile)) {
                    writer.write(jsonArray.toJSONString());
                    log.info("Backup written");
                } catch (IOException e) {
                    e.printStackTrace();
                    log.error("couldn't write into JSON Backup File");
                }

            } catch (Exception e) {
                e.printStackTrace();
                log.error("rommate list couldn't be initialized");
            }
            try {
                Thread.sleep(300000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    }

