import pygame
import sys

# Initialize Pygame
pygame.init()

# Set up the display
width, height = 800, 600
screen = pygame.display.set_mode((width, height))
pygame.display.set_caption("Move the Square")

# Colors
black = (0, 0, 0)
white = (255, 255, 255)

# Square properties
square_size = 50
square_x = width // 2 - square_size // 2
square_y = height // 2 - square_size // 2

# Main game loop
running = True
while running:
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            running = False

    # Key press events
    keys = pygame.key.get_pressed()
    if keys[pygame.K_a]:
        square_x -= 5
    if keys[pygame.K_d]:
        square_x += 5
    if keys[pygame.K_w]:
        square_y -= 5
    if keys[pygame.K_s]:
        square_y += 5

    # Drawing
    screen.fill(black)
    pygame.draw.rect(screen, white, (square_x, square_y, square_size, square_size))
    
    # Update display
    pygame.display.flip()

    # Frame rate
    pygame.time.Clock().tick(60)

# Quit Pygame
pygame.quit()
sys.exit()
