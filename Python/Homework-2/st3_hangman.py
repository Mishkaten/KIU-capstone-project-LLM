import random

def get_word():
    with open('hangman-words.txt', 'r') as file:
        words = file.read().splitlines()
    return random.choice(words)

def display_intro():
    print("\n_______________________________________________")
    print(" _                                             ")
    print("| |                                            ")
    print("| |__   __ _ _ __   __ _ _ __ ___   __ _ _ __  ")
    print("| '_ \\ / _` | '_ \\ / _` | '_ ` _ \\ / _` | '_ \\ ")
    print("| | | | (_| | | | | (_| | | | | | | (_| | | | |")
    print("|_| |_|\\__,_|_| |_|\\__, |_| |_| |_|\\__,_|_| |_|")
    print("                    __/ |                      ")
    print("                   |___/                       ")
    print("_____________________Rules_____________________")
    print("Guess the word one letter at a time. The number")
    print("of dashes are equivalent to the number of letters.")
    print("If the word does not contain the suggested letter,")
    print("a piece of hangman's gallow is added")
    print("Goal is to guess the word before the man hangs!")
    print("_______________________________________________\n")

def display_end(win, word):
    if win:
        print("Congratulations! You've guessed the word correctly!")
    else:
        print("Game Over! The correct word was:", word)
    print("Do you want to play again? (yes/no)")

def display_hangman(lives):
    stages = [  # Final state: head, torso, both arms, and both legs
                """
                   --------
                   |      |
                   |      O
                   |     \\|/
                   |      |
                   |     / \\
                """,
                # Head, torso, both arms, and one leg
                """
                   --------
                   |      |
                   |      O
                   |     \\|/
                   |      |
                   |     /
                """,
                # Head, torso, and both arms
                """
                   --------
                   |      |
                   |      O
                   |     \\|/
                   |      |
                   |
                """,
                # Head, torso, and one arm
                """
                   --------
                   |      |
                   |      O
                   |     \\|
                   |      |
                   |
                """,
                # Head and torso
                """
                   --------
                   |      |
                   |      O
                   |      |
                   |      |
                   |
                """,
                # Head
                """
                   --------
                   |      |
                   |      O
                   |
                   |
                   |
                """,
                # Initial empty state
                """
                   --------
                   |      |
                   |
                   |
                   |
                   |
                """
    ]
    print(stages[6 - lives])

def valid(input_char):
    return len(input_char) == 1 and input_char.isalpha() and input_char.islower()

def play():
    word = get_word()
    word_length = len(word)
    guessed = ['_'] * word_length
    guessed_letters = set()
    lives = 6
    win = False

    while lives > 0 and '_' in guessed:
        print(''.join(guessed))
        display_hangman(lives)
        guess = input("Guess a letter: ").lower()

        if not valid(guess):
            print("Invalid input, please enter a single lowercase letter.")
            continue

        if guess in guessed_letters:
            print("You already guessed that letter.")
            continue

        guessed_letters.add(guess)

        if guess in word:
            for i in range(word_length):
                if word[i] == guess:
                    guessed[i] = guess
        else:
            lives -= 1

    if '_' not in guessed:
        win = True

    display_hangman(lives)
    display_end(win, word)
    return win

def hangman():
    while True:
        display_intro()
        result = play()
        if input("Play again? (yes/no): ").lower() != 'yes':
            break

if __name__ == "__main__":
    hangman()