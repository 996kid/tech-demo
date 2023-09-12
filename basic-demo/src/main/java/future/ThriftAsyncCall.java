package future;

@FunctionalInterface
public interface ThriftAsyncCall {
    void invoke() throws Exception;
}