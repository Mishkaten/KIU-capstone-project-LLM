import pygame
import random

class Snake:
    def __init__(self, x, y, size):
        self.x = x
        self.y = y
        self.size = size
        self.cells = [(x, y)]
        self.direction = 'RIGHT'
        self.head_image = pygame.image.load('snake-head.png')
        self.head_image = pygame.transform.scale(self.head_image, (size, size))

    def draw(self, screen):
        for cell in self.cells:
            if cell == self.cells[0]:
                screen.blit(self.head_image, cell)
            else:
                pygame.draw.rect(screen, (0, 255, 0), (cell[0], cell[1], self.size, self.size))

    def move(self):
        if self.direction == 'RIGHT':
            self.x += self.size
        elif self.direction == 'LEFT':
            self.x -= self.size
        elif self.direction == 'UP':
            self.y -= self.size
        elif self.direction == 'DOWN':
            self.y += self.size

        self.cells.insert(0, (self.x, self.y))
        self.cells.pop()

    def grow(self):
        self.cells.append(self.cells[-1])

    def change_direction(self, direction):
        if (direction == 'RIGHT' and self.direction != 'LEFT') or \
           (direction == 'LEFT' and self.direction != 'RIGHT') or \
           (direction == 'UP' and self.direction != 'DOWN') or \
           (direction == 'DOWN' and self.direction != 'UP'):
            self.direction = direction

    def check_collision(self, width, height):
        if self.x < 0 or self.x >= width or self.y < 40 or self.y >= height:
            return True
        for cell in self.cells[1:]:
            if cell == (self.x, self.y):
                return True
        return False

class Food:
    def __init__(self, size):
        self.size = size
        self.x = round(random.randrange(0, 1200 - size) / size) * size
        self.y = round(random.randrange(40, 700 - size) / size) * size
        self.image = pygame.image.load('food.png')
        self.image = pygame.transform.scale(self.image, (size, size))

    def draw(self, screen):
        screen.blit(self.image, (self.x, self.y))

class SuperFood(Food):
    def __init__(self, size):
        super().__init__(size)
        self.image = pygame.image.load('super-food.png')
        self.image = pygame.transform.scale(self.image, (size, size))

class App:
    def __init__(self):
        self.running = False
        self.clock = None
        self.screen = None
        self.snake = None
        self.food = None
        self.super_food = None
        self.cell_size = 20
        self.score = 0
        self.high_scores = self.load_high_scores()
        pygame.font.init()  # Initialize the font module
        pygame.mixer.init()
        self.font = pygame.font.Font(None, 36)
        self.background_image = pygame.image.load('background.jpg')
        self.background_image = pygame.transform.scale(self.background_image, (1200, 700))
        self.eat_sound = pygame.mixer.Sound('eat.wav')
        self.crash_sound = pygame.mixer.Sound('crash.wav')
        self.music_playing = True

    def run(self):
        self.init()
        while self.running:
            self.update()
            self.render()
        self.cleanUp()

    def init(self):
        self.screen = pygame.display.set_mode((1200, 700))
        pygame.display.set_caption("Snake Game")
        pygame.mixer.music.load('background-music.mp3')
        pygame.mixer.music.play(-1)

        self.clock = pygame.time.Clock()
        self.running = True
        self.snake = Snake(600, 300, self.cell_size)
        self.food = Food(self.cell_size)
        self.super_food = None

    def update(self):
        self.events()
        self.snake.move()

        if self.snake.check_collision(1200, 700):
            self.crash_sound.play()
            self.game_over()

        if (self.snake.x, self.snake.y) == (self.food.x, self.food.y):
            self.eat_sound.play()
            self.snake.grow()
            self.score += 1
            self.food = Food(self.cell_size)
            if random.randint(1, 10) == 1:
                self.super_food = SuperFood(self.cell_size)

        if self.super_food and (self.snake.x, self.snake.y) == (self.super_food.x, self.super_food.y):
            self.eat_sound.play()
            self.score += 3
            self.super_food = None

    def events(self):
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                self.running = False
            elif event.type == pygame.KEYDOWN:
                if event.key == pygame.K_RIGHT or event.key == pygame.K_d:
                    self.snake.change_direction('RIGHT')
                elif event.key == pygame.K_LEFT or event.key == pygame.K_a:
                    self.snake.change_direction('LEFT')
                elif event.key == pygame.K_UP or event.key == pygame.K_w:
                    self.snake.change_direction('UP')
                elif event.key == pygame.K_DOWN or event.key == pygame.K_s:
                    self.snake.change_direction('DOWN')
                elif event.key == pygame.K_m:
                    self.toggle_music()

    def render(self):
        self.screen.blit(self.background_image, (0, 0))
        self.snake.draw(self.screen)
        self.food.draw(self.screen)

        if self.super_food:
            self.super_food.draw(self.screen)

        score_text = self.font.render(f"Score: {self.score}", True, (255, 255, 255))
        self.screen.blit(score_text, (10, 10))

        pygame.draw.line(self.screen, (255, 255, 255), (0, 40), (1200, 40), 2)

        pygame.display.flip()
        self.clock.tick(10)

    def game_over(self):
        self.update_high_scores()
        self.save_high_scores()

        while True:
            self.screen.fill((0, 0, 0))
            game_over_text = self.font.render("Game Over", True, (255, 255, 255))
            score_text = self.font.render(f"Score: {self.score}", True, (255, 255, 255))
            replay_text = self.font.render("Press Space to Replay", True, (255, 255, 255))

            self.screen.blit(game_over_text, (1200 // 2 - game_over_text.get_width() // 2, 200))
            self.screen.blit(score_text, (1200 // 2 - score_text.get_width() // 2, 250))
            self.screen.blit(replay_text, (1200 // 2 - replay_text.get_width() // 2, 300))

            self.display_high_scores()

            pygame.display.flip()

            for event in pygame.event.get():
                if event.type == pygame.QUIT:
                    self.running = False
                    return
                elif event.type == pygame.KEYDOWN:
                    if event.key == pygame.K_SPACE:
                        self.reset_game()
                        return

    def reset_game(self):
        self.snake = Snake(600, 300, self.cell_size)
        self.food = Food(self.cell_size)
        self.super_food = None
        self.score = 0

    def load_high_scores(self):
        try:
            with open('high_scores.txt', 'r') as file:
                return [int(score.strip()) for score in file.readlines()]
        except FileNotFoundError:
            return []

    def save_high_scores(self):
        with open('high_scores.txt', 'w') as file:
            for score in self.high_scores:
                file.write(str(score) + '\n')

    def update_high_scores(self):
        self.high_scores.append(self.score)
        self.high_scores.sort(reverse=True)
        self.high_scores = self.high_scores[:10]

    def display_high_scores(self):
        high_scores_text = self.font.render("High Scores:", True, (255, 255, 255))
        self.screen.blit(high_scores_text, (1200 // 2 - high_scores_text.get_width() // 2, 350))

        for i, score in enumerate(self.high_scores, start=1):
            score_text = self.font.render(f"{i}. {score}", True, (255, 255, 255))
            self.screen.blit(score_text, (1200 // 2 - score_text.get_width() // 2, 350 + i * 30))

    def toggle_music(self):
        if self.music_playing:
            pygame.mixer.music.pause()
        else:
            pygame.mixer.music.unpause()
        self.music_playing = not self.music_playing

    def cleanUp(self):
        pygame.quit()

if __name__ == "__main__":
    app = App()
    app.run()