import pygame
import random
import sys

pygame.init()

# Screen dimensions
WIDTH, HEIGHT = 640, 480
screen = pygame.display.set_mode((WIDTH, HEIGHT))
pygame.display.set_caption("Snake Game")

# Colors
WHITE = (255, 255, 255)
GREEN = (0, 255, 0)
RED = (255, 0, 0)

# Directions
UP = (0, -10)
DOWN = (0, 10)
LEFT = (-10, 0)
RIGHT = (10, 0)

# Initial settings
snake_pos = [(WIDTH//2, HEIGHT//2 + i*10) for i in range(5)]
snake_dir = RIGHT
food_pos = (random.randint(0, (WIDTH-10)//10) * 10, random.randint(0, (HEIGHT-10)//10) * 10)
score = 0

# Game loop
clock = pygame.time.Clock()
running = True
while running:
    screen.fill(WHITE)
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            running = False
        if event.type == pygame.KEYDOWN:
            if event.key == pygame.K_UP and snake_dir != DOWN:
                snake_dir = UP
            elif event.key == pygame.K_DOWN and snake_dir != UP:
                snake_dir = DOWN
            elif event.key == pygame.K_LEFT and snake_dir != RIGHT:
                snake_dir = LEFT
            elif event.key == pygame.K_RIGHT and snake_dir != LEFT:
                snake_dir = RIGHT

    # Move snake
    new_head = (snake_pos[0][0] + snake_dir[0], snake_pos[0][1] + snake_dir[1])
    if new_head in snake_pos or new_head[0] < 0 or new_head[0] >= WIDTH or new_head[1] < 0 or new_head[1] >= HEIGHT:
        print("Game Over! Score:", score)
        running = False
    snake_pos.insert(0, new_head)
    if new_head == food_pos:
        score += 10
        food_pos = (random.randint(0, (WIDTH-10)//10) * 10, random.randint(0, (HEIGHT-10)//10) * 10)
    else:
        snake_pos.pop()

    # Draw snake
    for pos in snake_pos:
        pygame.draw.rect(screen, GREEN, pygame.Rect(pos[0], pos[1], 10, 10))

    # Draw food
    pygame.draw.rect(screen, RED, pygame.Rect(food_pos[0], food_pos[1], 10, 10))

    pygame.display.update()
    clock.tick(10)

pygame.quit()
sys.exit()
