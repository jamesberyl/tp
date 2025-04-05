package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEDDING_ID;

import java.util.stream.Stream;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.DeleteTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.wedding.WeddingId;

/**
 * Parses input arguments and creates a new DeleteTaskCommand object.
 */
public class DeleteTaskCommandParser implements Parser<DeleteTaskCommand> {

    @Override
    public DeleteTaskCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_WEDDING_ID, PREFIX_TASK_INDEX);

        if (!arePrefixesPresent(argMultimap, PREFIX_WEDDING_ID, PREFIX_TASK_INDEX)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(DeleteTaskCommand.MESSAGE_USAGE);
        }

        String weddingIdStr = argMultimap.getValue(PREFIX_WEDDING_ID).get();
        String indexStr = argMultimap.getValue(PREFIX_TASK_INDEX).get();

        if (!WeddingId.isValidWeddingId(weddingIdStr)) {
            throw new ParseException(Messages.MESSAGE_INVALID_WEDDING_ID);
        }

        WeddingId weddingId = new WeddingId(weddingIdStr);
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(indexStr);
        } catch (NumberFormatException e) {
            throw new ParseException("Task index must be an integer.");
        }

        return new DeleteTaskCommand(weddingId, taskIndex);
    }

    private boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
