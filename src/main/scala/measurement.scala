case class Measurement(time: Long, location: Location) {
  def partition(partitioning: (Double, Double)): Grid = {
    val (timePartitioning, locationPartitioning) = partitioning

    val timePartition = (time/timePartitioning).toLong
    val locationPartition = location.partition(locationPartitioning)

    Grid(timePartition, locationPartition)
  }
}

// Holds an id together with a measurement
case class MeasurementID(id: Int, measurement: Measurement) {
  def partition(partitioning: (Double, Double)): GridID = {
    GridID(id, measurement.partition(partitioning))
  }
}

// Holds parition id for time and location
case class Grid(time: Long, location: LocationPartition)

// Holds an id together with a measurement
case class GridID(id: Int, grid: Grid)
