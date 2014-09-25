package scrummaster;

/**
 *
 * @author Nick
 */
public enum Candidate
{

    ANDREW("Andrew", "Kennedy"),
    AMANDA("Amanda", "Crawley"),
    DAVID("David", "Kershaw"),
    JEFF("Jeff", "Peh"),
    JOHN("John", "Everick"),
    JUSTIN("Justin", "Kennedy"),
    MORY("Mory", "Savane"),
    NATHAN("Nathan", "Langton"),
    NICK("Nick", "MacIsaac"),
    RANDY("Randy", "Howlett"),
    YASIR("Yasir", "Faruqi");
    private final String lastName;
    private final String firstName;
    private final String ponyPath;

    private Candidate(String firstName, String lastName)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ponyPath = buildPath(firstName);
    }

    public String getFirstName()
    {
        return this.firstName;
    }

    public String getLastName()
    {
        return this.lastName;
    }

    public String getName()
    {
        return this.firstName + " " + this.lastName;
    }

    public String getPonyPath()
    {
        return this.ponyPath;
    }

    private String buildPath(String name)
    {
        StringBuilder sb = new StringBuilder("/img/candidate/");
        sb.append(name.toLowerCase());
        sb.append("/");
        sb.append(name.toLowerCase());
        sb.append("Pony.gif");
        return sb.toString();
    }
}
