package ar.edu.utn.frc.tup.lciii.dtos.login;
//jackson nos ayuda a convertir de json a un objeto
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@JsonTypeInfo(use= JsonTypeInfo.Id.NAME, property = "identity_type", include = JsonTypeInfo.As.EXISTING_PROPERTY, visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = UserNameIdentity.class, name = "USERNAME"),
        @JsonSubTypes.Type(value = EmailIdentity.class, name = "EMAIL")
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Identity {

    @Schema(title = "Type of identity used to logged in",
            description = "The type of identity provided to logged in",
            example = "USERNAME or EMAIL",
            nullable = false)
    @NotNull(message = "identity_type can't by null")
    @JsonProperty("identity_type")
    private IdentityType identityType;
}
