package pl.kkowalczyk.census;

public class PersonalDataProviderSubject implements IPersonalDataProvider {

    private static PersonalDataProviderSubject _instance;
    public static PersonalDataProviderSubject Instance()
    {
        if(_instance == null)
        {
            _instance = new PersonalDataProviderSubject();
        }

        return _instance;
    }

    @Override
    public void ProvideData(String name, String surname, String position)
    {
        PersonalDataHolder.Instance().AddPerson(name, surname, position);
    }
}

