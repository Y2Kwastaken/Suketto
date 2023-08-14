package sh.miles.suketto.nms.v1_20_1;

import jline.internal.Preconditions;
import net.md_5.bungee.api.chat.BaseComponent;
import org.jetbrains.annotations.NotNull;
import sh.miles.suketto.nms.ContainerHandle;
import sh.miles.suketto.nms.container.SukettoContainer;
import sh.miles.suketto.nms.container.SukettoContainerType;
import sh.miles.suketto.nms.v1_20_1.container.AnvilContainerImpl;
import sh.miles.suketto.nms.v1_20_1.container.EnchantingContainerImpl;
import sh.miles.suketto.nms.v1_20_1.container.GrindstoneContainerImpl;
import sh.miles.suketto.nms.v1_20_1.container.SmithingContainerImpl;

public class ContainerHandleImpl implements ContainerHandle {


    @NotNull
    @Override
    public SukettoContainer createContainer(@NotNull final BaseComponent title, @NotNull SukettoContainerType type) {
        Preconditions.checkNotNull(title);
        Preconditions.checkNotNull(type);

        switch (type) {
            case ANVIL -> new AnvilContainerImpl(title);
            case SMITHING -> new SmithingContainerImpl(title);
            case ENCHANTING -> new EnchantingContainerImpl(title);
            case GRINDSTONE -> new GrindstoneContainerImpl(title);
            default ->
                    throw new IllegalArgumentException("The provided inventory type can not be created for this version");
        }

        throw new IllegalArgumentException("The provided inventory type can not be created for this version");
    }

}
