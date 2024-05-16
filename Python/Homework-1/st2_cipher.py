def encryptAtbashCipher(plain_text):
    # Define the translation table: maps each letter to its reversed counterpart
    alphabet = 'abcdefghijklmnopqrstuvwxyz'
    reversed_alphabet = alphabet[::-1]
    atbash_translation = str.maketrans(alphabet + alphabet.upper(), reversed_alphabet + reversed_alphabet.upper())

    # Encrypt the text by translating it using the Atbash cipher
    encrypted_text = plain_text.translate(atbash_translation)
    return encrypted_text

def decryptAtbashCipher(encrypted_text):
    # Atbash cipher is symmetric: encryption and decryption processes are identical
    return encryptAtbashCipher(encrypted_text)

# Example usage
plain_text = "atbash"
encrypted_text = encryptAtbashCipher(plain_text)
decrypted_text = decryptAtbashCipher(encrypted_text)

print(f"Plain Text: {plain_text}")
print(f"Encrypted text: {encrypted_text}")
print(f"Decrypted text: {decrypted_text}")
