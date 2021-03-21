package by.dma.web.models;

public enum Position {
    HOUSEKEEPING, FRONT_DESK, SECURITY, CONCIERGE;

    public String toString() {
        switch (this) {
        case CONCIERGE:
            return "Concierge";
        case HOUSEKEEPING:
            return "Housekeeping";
        case FRONT_DESK:
            return "Front Desk";
        case SECURITY:
            return "Security";
        }
        return "";
    }
}
