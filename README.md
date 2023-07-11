# Textify

Textify is a command-line tool designed to perform various text transformations on a file. It offers several commands to manipulate text in different ways.

## Usage

To use Textify, run the following command:

textify [OPTION] FILE

Replace `[OPTION]` with one of the available commands and `FILE` with the path to the input file.

## Available Commands

- `-x`: Removes empty lines from the input file.
- `-g`: Replaces all occurrences of a substring `old` with a string `new` in each line.
- `-w spacing`: Removes whitespace from lines based on the specified interval: `all` (removes all whitespace), `leading` (removes leading whitespace), or `trailing` (removes trailing whitespace).
- `-r old new`: Replaces the first instance of a substring `old` with a string `new` in each line.
- `-p ch num`: Pads the beginning of each line with the specified character `ch` until the line length reaches the specified number `num`.
- `-s suffix`: Adds the specified suffix to the end of each line.

## Examples

Here are some examples of how to use Textify:

- Remove empty lines from a file:

textify -x input.txt

- Replace all occurrences of a substring in each line:

textify -g old new input.txt

- Remove trailing whitespace from lines:

textify -w trailing input.txt

- Replace the first instance of a substring in each line:

textify -r old new input.txt

- Pad the beginning of each line with a symbol:

textify -p symbol 10 input.txt

- Add a suffix to the end of each line:

textify -s suffix input.txt

Feel free to explore the different commands and experiment with different options to manipulate your text files using Textify.
