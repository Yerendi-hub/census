package pl.kkowalczyk.census;

public class Person
{
    public Person(IFlyweight name, IFlyweight surname, IFlyweight position)
    {
        _name = name;
        _surname = surname;
        _position = position;
    }

    private IFlyweight _name;
    private IFlyweight _surname;
    private IFlyweight _position;

    public String GetName()
    {
        return _name.GetState();
    }

    public String GetSurname()
    {
        return _surname.GetState();
    }

    public String GetPosition()
    {
        return _position.GetState();
    }

    public String ToString()
    {
        return String.format("%s, %s, %s", GetName(), GetSurname(), GetPosition());
    }

    public String GetSaveString()
    {
        return String.format("%d,%d,%d\n", _name.GetId(), _surname.GetId(), _position.GetId());
    }
}

