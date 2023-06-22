import os
import random
import string
import sys

def load_word_pool(file_path):
    with open(file_path, 'r') as file:
        word_pool = file.read().split()
    return word_pool


def generate_random_text(word_pool, size_mb):
    words_per_mb = 100000
    total_words = int(size_mb * words_per_mb)

    list_of_words = random.choices(word_pool, k=total_words)
    random_text = ' '.join(list_of_words)
    return random_text


def save_text_to_file(text, file_path):
    with open(file_path, 'w') as file:
        file.write(text)


def main():
    if len(sys.argv) != 2:
        print('Usage: python text.py <file_size_in_mb>')
        return

    try:
        size_mb = float(sys.argv[1])
    except ValueError:
        print('Invalid file size. Please provide a number value.')
        return

    word_pool_file_path = "words.txt"
    word_pool = load_word_pool(word_pool_file_path)

    random_text = generate_random_text(word_pool, size_mb)

    current_directory = os.getcwd()

    file_name = f'random_text_{size_mb}mb.txt'
    file_path = os.path.join(current_directory, file_name)
    save_text_to_file(random_text, file_path)
    print(f'Successfully generated {file_name} in {current_directory}')


if __name__ == '__main__':
    main()
