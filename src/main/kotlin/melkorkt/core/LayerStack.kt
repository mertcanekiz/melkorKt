package melkorkt.core

class LayerStack {
    var layers: MutableList<Layer> = mutableListOf()
    var layerInsertIndex: Int = 0

    fun pushLayer(layer: Layer) {
        layers.add(layerInsertIndex, layer)
        layerInsertIndex++
        layer.onAttach()
    }

    fun pushOverlay(overlay: Layer) {
        layers.add(overlay)
        overlay.onAttach()
    }

    fun popLayer(layer: Layer) {
        layers.removeAt(layers.lastIndexOf(layer))
        layerInsertIndex--
    }

    fun popOverLay(overlay: Layer) {
        overlay.onDetach()
        layers.removeAt(layers.lastIndexOf(overlay))
    }
}