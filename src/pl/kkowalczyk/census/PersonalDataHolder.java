package pl.kkowalczyk.census;

import javax.swing.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class PersonalDataHolder {
    private static PersonalDataHolder _instance;
    public static PersonalDataHolder Instance()
    {
        if(_instance == null)
        {
            _instance = new PersonalDataHolder();
        }

        return _instance;
    }

    private List<Person> _people = new ArrayList<>();

    public PersonalDataHolder()
    {
        try{
            File file = new File("people.txt");
            file.createNewFile();
            BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream("people.txt"), StandardCharsets.UTF_8));
            String line;
            while((line=br.readLine())!=null){
                String[] split=line.split(",");
                int nameId = Integer.parseInt(split[0].trim());
                int surnameId = Integer.parseInt(split[1].trim());
                int posId = Integer.parseInt(split[2].trim());
                //System.out.println(String.format("%d %d %d", nameId, surnameId, posId));
                IFlyweight name = FlyweightFactory.Instance().GetDataById(nameId);
                IFlyweight surname = FlyweightFactory.Instance().GetDataById(surnameId);
                IFlyweight pos = FlyweightFactory.Instance().GetDataById(posId);
                //System.out.println(String.format("%s %s %s", name.GetState(), surname.GetState(), pos.GetState()));
                _people.add(new Person(name,surname,pos));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void AddPerson(String name, String surname, String position)
    {
        Person person = new Person(FlyweightFactory.Instance().GetData(name),
                FlyweightFactory.Instance().GetData(surname), FlyweightFactory.Instance().GetData(position));
        _people.add(person);
    }

    public void Save() throws IOException {
        FileOutputStream outputStream = new FileOutputStream("people.txt");

        for (int i = 0; i < _people.size(); i++)
        {
            byte[] strToBytes = _people.get(i).GetSaveString().getBytes();
            outputStream.write(strToBytes);
        }
        outputStream.close();
    }

    public void ListPeople()
    {
       for (int i = 0; i < _people.size(); i++)
       {
           System.out.println(_people.get(i).ToString());
       }
    }
}

