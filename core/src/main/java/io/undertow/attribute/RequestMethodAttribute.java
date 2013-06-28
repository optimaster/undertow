package io.undertow.attribute;

import io.undertow.server.HttpServerExchange;

/**
 * The request method
 *
 * @author Stuart Douglas
 */
public class RequestMethodAttribute implements ExchangeAttribute {

    public static final String REQUEST_METHOD_SHORT = "%m";
    public static final String REQUEST_METHOD = "%{METHOD}";

    public static final ExchangeAttribute INSTANCE = new RequestMethodAttribute();

    private RequestMethodAttribute() {

    }

    @Override
    public String readAttribute(final HttpServerExchange exchange) {
        return exchange.getRequestMethod().toString();
    }

    @Override
    public void writeAttribute(final HttpServerExchange exchange, final String newValue) throws ReadOnlyAttributeException {
        throw new ReadOnlyAttributeException("Request method", newValue);
    }

    public static final class Builder implements ExchangeAttributeBuilder {

        @Override
        public String name() {
            return "Request method";
        }

        @Override
        public ExchangeAttribute build(final String token) {
            if (token.equals(REQUEST_METHOD) || token.equals(REQUEST_METHOD_SHORT)) {
                return RequestMethodAttribute.INSTANCE;
            }
            return null;
        }
    }
}
