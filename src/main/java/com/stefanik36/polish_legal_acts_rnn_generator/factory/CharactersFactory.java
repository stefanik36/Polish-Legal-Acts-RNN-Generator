package com.stefanik36.polish_legal_acts_rnn_generator.factory;

import io.vavr.collection.List;

public class CharactersFactory {

    private List<Character> characters = List.empty();


    public CharacterFactoryBuilder builder() {
        return new CharacterFactoryBuilder();
    }

    public List<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }

    public class CharacterFactoryBuilder {
        private final List<Character> ACTS_CHARACTERS = List.of('(', ')', '-', '–', ',', '.', ':', ';', ' ', '\n', '\t', '§', '<', '>');
        private final List<Character> LOWERCASE_POLISH_LETTERS = List.of('ą', 'ć', 'ę', 'ł', 'ń', 'ó', 'ś', 'ź', 'ż');
        private final List<Character> UPPERCASE_POLISH_LETTERS = List.of('Ą', 'Ć', 'Ę', 'Ł', 'Ń', 'Ó', 'Ś', 'Ź', 'Ż');
        private final List<Character> LOWERCASE_LETTERS = List.of('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'w', 'v', 'x', 'y', 'z');
        private final List<Character> UPPERCASE_LETTERS = List.of('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'W', 'V', 'X', 'Y', 'Z');
        private final List<Character> DIGITS = List.of('0', '1', '2', '3', '4', '5', '6', '7', '8', '9');

        private List<Character> characters = List.empty();

        public CharacterFactoryBuilder lowercaseLetters() {
            characters = characters.appendAll(LOWERCASE_LETTERS);
            return this;
        }

        public CharacterFactoryBuilder uppercaseLetters() {
            characters = characters.appendAll(UPPERCASE_LETTERS);
            return this;
        }

        public CharacterFactoryBuilder digits() {
            characters = characters.appendAll(DIGITS);
            return this;
        }

        public CharacterFactoryBuilder lowercasePolishLetters() {
            characters = characters.appendAll(LOWERCASE_POLISH_LETTERS);
            return this;
        }

        public CharacterFactoryBuilder uppercasePolishLetters() {
            characters = characters.appendAll(UPPERCASE_POLISH_LETTERS);
            return this;
        }

        public CharacterFactoryBuilder actsCharacters() {
            characters = characters.appendAll(ACTS_CHARACTERS);
            return this;
        }

        public CharacterFactoryBuilder other(char... other) {
            characters = characters.appendAll(List.ofAll(other));
            return this;
        }

        public CharactersFactory build() {
            CharactersFactory charactersFactory = new CharactersFactory();
            charactersFactory.setCharacters(characters);
            return charactersFactory;
        }
    }
}
