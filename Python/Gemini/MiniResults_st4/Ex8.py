import pygame

# Initialize Pygame
pygame.init()

# Set up the screen
screen_width = 800
screen_height = 600
screen = pygame.display.set_mode((screen_width, screen_height))
pygame.display.set_caption("Move the Square")

# Square attributes
square_color = (255, 0, 0)  # Red
square_size = 50
square_x = screen_width // 2 - square_size // 2  # Center horizontally
square_y = screen_height // 2 - square_size // 2 # Center vertically
square_speed = 5

# Game loop
running = True
while running:
    # Handle events
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            running = False

    # Get pressed keys
    keys = pygame.key.get_pressed()
    if keys[pygame.K_a]:
        square_x -= square_speed
    if keys[pygame.K_d]:
        square_x += square_speed
    if keys[pygame.K_w]:
        square_y -= square_speed
    if keys[pygame.K_s]:
        square_y += square_speed

    # Keep the square within the screen boundaries
    square_x = max(0, min(square_x, screen_width - square_size))
    square_y = max(0, min(square_y, screen_height - square_size))

    # Clear the screen
    screen.fill((0, 0, 0))  # Black background

    # Draw the square
    pygame.draw.rect(screen, square_color, (square_x, square_y, square_size, square_size))

    # Update the display
    pygame.display.flip()

# Quit Pygame
pygame.quit()
