public class DependencyParser {
    private final String name;
    private final String[] group;
    private final String domain;

    public DependencyParser(String dependency) {
        name = dependency.substring(dependency.indexOf(":") + 1, dependency.length());
        domain = dependency.substring(0, dependency.indexOf(":"));
        group = domain.split(".");
    }

    public String getName() {
        return name;
    }

    public String[] getGroup() {
        return group;
    }

    public String getDomain() {
        return domain;
    }
}
