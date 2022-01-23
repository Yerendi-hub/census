package pl.kkowalczyk.census;

public class Flyweight implements IFlyweight
{
    private String _state;
    private int _id;

    public Flyweight(String state, int id)
    {
        _id = id;
        _state = state;
    }

    public String GetState()
    {
        return _state;
    }

    public int GetId()
    {
        return _id;
    }

    public String GetSaveString()
    {
        return String.format("%d,%s\n", _id, _state);
    }
}
