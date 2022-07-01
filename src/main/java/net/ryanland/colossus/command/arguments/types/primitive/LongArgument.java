package net.ryanland.colossus.command.arguments.types.primitive;

import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.ryanland.colossus.command.arguments.ArgumentOptionData;
import net.ryanland.colossus.command.arguments.parsing.exceptions.ArgumentException;
import net.ryanland.colossus.command.arguments.parsing.exceptions.MalformedArgumentException;
import net.ryanland.colossus.events.MessageCommandEvent;
import net.ryanland.colossus.events.SlashEvent;

public class LongArgument extends NumberArgument<Long> {

    private Long min = null;
    private Long max = null;

    public LongArgument() {
    }

    public LongArgument(long min, long max) {
        this.min = min;
        this.max = max;
    }

    public LongArgument setMinimum(long min) {
        this.min = min;
        return this;
    }

    public LongArgument setMaximum(long max) {
        this.max = max;
        return this;
    }

    private long check(long value) throws ArgumentException {
        if (value >= min && value <= max)
            return value;
        else
            throw new MalformedArgumentException("Number must be in between " + min + " and " + max + ".");
    }

    @Override
    public ArgumentOptionData getArgumentOptionData() {
        ArgumentOptionData data = new ArgumentOptionData(OptionType.INTEGER);
        if (min != null) data.setMinValue(min);
        if (max != null) data.setMaxValue(max);
        return data;
    }

    @Override
    public Long resolveSlashCommandArgument(SlashEvent event, OptionMapping arg) throws ArgumentException {
        return arg.getAsLong();
    }

    @Override
    public Long resolveMessageCommandArgument(MessageCommandEvent event, String arg) throws ArgumentException {
        return check(Long.parseLong(arg));
    }
}
