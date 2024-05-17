import pygame
import random

class Snake:
    def __init__(self):
        self.cells = [(5, 5), (4, 5), (3, 5), (2, 5), (1, 5)]
        self.direction = "RIGHT"
        self.color = (0, 255, 0)

    def draw(self, screen):
        for cell in self.cells:
            pygame.draw.rect(screen, self.color, (cell[0] * 20, cell[1] * 20, 20, 20))

    def move(self):
        head = self.cells[0]
        if self.direction == "RIGHT":
            new_head = (head[0] + 1, head[1])
        elif self.direction == "LEFT":
            new_head = (head[0] - 1, head[1])
        elif self.direction == "UP":
            new_head = (head[0], head[1] - 1)
        elif self.direction == "DOWN":
            new_head = (head[0], head[1] + 1)

        self.cells.insert(0, new_head)
        self.cells.pop()

    def grow(self):
        tail = self.cells[-1]
        self.cells.append(tail)

    def check_collision(self, width, height):
        head = self.cells[0]
        if head[0] < 0 or head[0] >= width or head[1] < 0 or head[1] >= height:
            return True
        if head in self.cells[1:]:
            return True
        return False

class Food:
    def __init__(self, width, height):
        self.position = (random.randint(0, width - 1), random.randint(0, height - 1))
        self.color = (255, 0, 0)

    def draw(self, screen):
        pygame.draw.rect(screen, self.color, (self.position[0] * 20, self.position[1] * 20, 20, 20))

class App:
    def __init__(self):
        self.running = False
        self.clock = None
        self.screen = None
        self.snake = None
        self.food = None
        self.score = 0

    def run(self):
        self.init()
        while self.running:
            self.update()
            self.render()
        self.cleanUp()

    def init(self):
        self.screen = pygame.display.set_mode((800, 600))
        pygame.display.set_caption("Snake Game")

        self.clock = pygame.time.Clock()
        self.running = True

        self.snake = Snake()
        self.food = Food(40, 30)

    def update(self):
        self.events()
        self.snake.move()

        if self.snake.cells[0] == self.food.position:
            self.snake.grow()
            self.food = Food(40, 30)
            self.score += 1

        if self.snake.check_collision(40, 30):
            self.running = False

    def events(self):
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                self.running = False
            elif event.type == pygame.KEYDOWN:
                if event.key == pygame.K_UP or event.key == pygame.K_w:
                    if self.snake.direction != "DOWN":
                        self.snake.direction = "UP"
                elif event.key == pygame.K_DOWN or event.key == pygame.K_s:
                    if self.snake.direction != "UP":
                        self.snake.direction = "DOWN"
                elif event.key == pygame.K_LEFT or event.key == pygame.K_a:
                    if self.snake.direction != "RIGHT":
                        self.snake.direction = "LEFT"
                elif event.key == pygame.K_RIGHT or event.key == pygame.K_d:
                    if self.snake.direction != "LEFT":
                        self.snake.direction = "RIGHT"

    def render(self):
        self.screen.fill((0, 0, 0))
        self.snake.draw(self.screen)
        self.food.draw(self.screen)

        font = pygame.font.Font(None, 36)
        text = font.render(f"Score: {self.score}", True, (255, 255, 255))
        self.screen.blit(text, (10, 10))

        pygame.display.flip()
        self.clock.tick(10)

    def cleanUp(self):
        pass

if __name__ == "__main__":
    app = App()
    app.run()