package pl.kkowalczyk.census;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class FlyweightFactory {
    private static FlyweightFactory _instance;

    public static FlyweightFactory Instance() {
        if (_instance == null) {
            _instance = new FlyweightFactory();
        }

        return _instance;
    }

    Map<String, IFlyweight> _flyweights = new HashMap<String, IFlyweight>();
    private int _lastId;

    public FlyweightFactory() {
        try {
            File file = new File("flyweights.txt");
            file.createNewFile();
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("flyweights.txt"), StandardCharsets.UTF_8));
            String line;

            while ((line = br.readLine()) != null) {
                String[] split = line.split(",");
                int id = Integer.parseInt(split[0]);
                IFlyweight f = new Flyweight(split[1], id);

                if (id > _lastId) {
                    _lastId = id;
                }

                _flyweights.put(f.GetState(), f);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public IFlyweight GetData(String data) {
        if (_flyweights.containsKey(data)) {
            return _flyweights.get(data);
        } else {
            IFlyweight flyweight = new Flyweight(data, ++_lastId);
            _flyweights.put(data, flyweight);
            return flyweight;
        }
    }

    public IFlyweight GetDataById(int id) {
        for (Iterator<Map.Entry<String, IFlyweight>> i = _flyweights.entrySet().iterator(); i.hasNext(); ) {
            Map.Entry<String, IFlyweight> entry = i.next();
            if (id == (entry.getValue().GetId())) {
                return entry.getValue();
            }
        }

        return null;
    }

    public void Save() throws IOException {
        FileOutputStream outputStream = new FileOutputStream("flyweights.txt");

        for (Iterator<Map.Entry<String, IFlyweight>> i = _flyweights.entrySet().iterator(); i.hasNext(); ) {
            Map.Entry<String, IFlyweight> entry = i.next();
            byte[] strToBytes = entry.getValue().GetSaveString().getBytes();
            outputStream.write(strToBytes);
        }

        outputStream.close();
    }
}

