package pl.kkowalczyk.census;

public class PersonalDataProviderProxy implements IPersonalDataProvider {

    private static PersonalDataProviderProxy _instance;
    public static PersonalDataProviderProxy Instance()
    {
        if(_instance == null)
        {
            _instance = new PersonalDataProviderProxy();
        }

        return _instance;
    }

    @Override
    public void ProvideData(String name, String surname, String position)
    {
        name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        surname = surname.substring(0, 1).toUpperCase() + surname.substring(1).toLowerCase();
        PersonalDataProviderSubject.Instance().ProvideData(name, surname, position);
    }
}
