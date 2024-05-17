import pygame
import random

# Initialize Pygame
pygame.init()

# Set up the display
width, height = 800, 600
screen = pygame.display.set_mode((width, height))
pygame.display.set_caption('Snake Game')

# Colors
black = (0, 0, 0)
green = (0, 255, 0)
red = (255, 0, 0)

# Frame rate
clock = pygame.time.Clock()


class Snake:
    def __init__(self):
        self.length = 5
        self.positions = [((width // 40) * 20, (height // 40) * 20 + i * 20) for i in range(5)]
        self.direction = 'RIGHT'
        self.score = 0

    def draw(self, surface):
        for pos in self.positions:
            pygame.draw.rect(surface, green, pygame.Rect(pos[0], pos[1], 20, 20))

    def move(self):
        cur = self.positions[0]
        x, y = cur
        if self.direction == 'RIGHT':
            x += 20
        elif self.direction == 'LEFT':
            x -= 20
        elif self.direction == 'UP':
            y -= 20
        elif self.direction == 'DOWN':
            y += 20
        new_head = (x, y)

        # Game over conditions
        if x < 0 or x >= width or y < 0 or y >= height or new_head in self.positions:
            return False

        self.positions = [new_head] + self.positions[:-1]

        # Eating food
        if new_head == food_pos:
            self.positions.append(self.positions[-1])
            self.score += 1
            spawn_food()

        return True

    def change_direction(self, new_dir):
        # Avoid reversing direction
        dirs = {'RIGHT': 'LEFT', 'LEFT': 'RIGHT', 'UP': 'DOWN', 'DOWN': 'UP'}
        if new_dir != dirs[self.direction]:
            self.direction = new_dir


def spawn_food():
    global food_pos
    food_pos = (random.randint(0, (width - 20) // 20) * 20, random.randint(0, (height - 20) // 20) * 20)
    while food_pos in snake.positions:
        food_pos = (random.randint(0, (width - 20) // 20) * 20, random.randint(0, (height - 20) // 20) * 20)


# Initialize snake and food
snake = Snake()
spawn_food()

# Game loop
running = True
while running:
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            running = False
        elif event.type == pygame.KEYDOWN:
            if event.key == pygame.K_UP or event.key == pygame.K_w:
                snake.change_direction('UP')
            elif event.key == pygame.K_DOWN or event.key == pygame.K_s:
                snake.change_direction('DOWN')
            elif event.key == pygame.K_LEFT or event.key == pygame.K_a:
                snake.change_direction('LEFT')
            elif event.key == pygame.K_RIGHT or event.key == pygame.K_d:
                snake.change_direction('RIGHT')

    screen.fill(black)
    snake.draw(screen)
    pygame.draw.rect(screen, red, pygame.Rect(food_pos[0], food_pos[1], 20, 20))
    if not snake.move():
        running = False
    pygame.display.update()
    clock.tick(10)

pygame.quit()
