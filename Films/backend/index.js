const express = require('express');
const mongoose = require('mongoose');

const app = express();
const PORT = 3000;

mongoose.connect('mongodb+srv://jehernandezr:uaQRn7QoYvzOxnCt@cluster0.oqcimoq.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0', {
  useNewUrlParser: true,
  useUnifiedTopology: true,
});

const filmSchema = new mongoose.Schema({
  title: String,
});

const Film = mongoose.model('Film', filmSchema);

app.use(express.json());

// Routes
// POST http://localhost:3000/films
app.post('/films', async (req, res) => {
  const { title } = req.body;
  try {
    const film = new Film({ title });
    await film.save();
    res.status(201).send(film);
  } catch (err) {
    res.status(400).send(err.message);
  }
});

app.get('/films', async (req, res) => {
  try {
    const films = await Film.find();
    res.send(films);
  } catch (err) {
    res.status(500).send(err.message);
  }
});

app.get('/films/:title', async (req, res) => {
  const title = req.params.title;
  try {
    const films = await Film.find({ title });
    res.send(films);
  } catch (err) {
    res.status(500).send(err.message);
  }
});

// Start server
app.listen(PORT, () => {
  console.log(`Server is running on http://localhost:${PORT}`);
});
