def displayIntro():
    # Print ASCII art and rules.

def getWord():
    # Read words from file and select randomly.

def valid(input):
    # Check if input is a single lowercase letter.

def displayHangman(lives):
    # Visualize the hangman according to the number of lives left.

def play():
    word = getWord()
    guessed = ['_'] * len(word)
    lives = 5
    while lives > 0 and '_' in guessed:
        displayHangman(lives)
        print('Current word:', ''.join(guessed))
        guess = input('Guess a letter: ')
        if not valid(guess):
            continue
        if guess in word:
            for i in range(len(word)):
                if word[i] == guess:
                    guessed[i] = guess
        else:
            lives -= 1
    return '_' not in guessed

def displayEnd(result):
    if result:
        print("Congratulations, you won!")
    else:
        print("Sorry, you lost. The word was:", word)

def hangman():
    while True:
        displayIntro()
        result = play()
        displayEnd(result)
        if input("Do you want to play again? (yes/no): ").lower() != 'yes':
            break

if __name__ == "__main__":
    hangman()
