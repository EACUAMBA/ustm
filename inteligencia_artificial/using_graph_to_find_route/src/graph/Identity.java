package graph;

import java.util.Objects;
import java.util.UUID;

import static java.util.Objects.isNull;

public abstract class Identity {

    private String uuid = UUID.randomUUID().toString();

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(isNull(obj)){
            return false;
        }

        if(!this.getClass().isInstance(obj)){
            return false;
        }

        Identity identity = (Identity) obj;
        return Objects.equals(this.uuid, identity.uuid);
    }
}
