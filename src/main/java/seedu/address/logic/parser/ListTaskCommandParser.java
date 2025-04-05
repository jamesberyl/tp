package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_WEDDING_ID;

import java.util.stream.Stream;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.ListTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.wedding.WeddingId;

/**
 * Parses input arguments and creates a new ListTaskCommand object.
 */
public class ListTaskCommandParser implements Parser<ListTaskCommand> {

    @Override
    public ListTaskCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_WEDDING_ID);

        if (!arePrefixesPresent(argMultimap, PREFIX_WEDDING_ID)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(ListTaskCommand.MESSAGE_USAGE);
        }

        String weddingIdStr = argMultimap.getValue(PREFIX_WEDDING_ID).get();

        if (!WeddingId.isValidWeddingId(weddingIdStr)) {
            throw new ParseException(Messages.MESSAGE_INVALID_WEDDING_ID);
        }
        WeddingId weddingId = new WeddingId(weddingIdStr);

        return new ListTaskCommand(weddingId);
    }

    private boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
