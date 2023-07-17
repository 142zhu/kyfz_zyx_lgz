<template>
  <div ref="chartContainer" style="width: 100%; height: 400px" />
</template>

<script>
import { GraphChart } from 'echarts/charts'
import { LegendComponent, TitleComponent, TooltipComponent } from 'echarts/components'
import * as echarts from 'echarts/core'
import { CanvasRenderer } from 'echarts/renderers'
import { onMounted, ref } from 'vue'

echarts.use([
  TitleComponent,
  TooltipComponent,
  LegendComponent,
  GraphChart,
  CanvasRenderer
])

export default {
  setup() {
    const chartContainer = ref(null)
    onMounted(() => {
      const myChart = echarts.init(chartContainer.value)
      myChart.showLoading()

      const graph = require('./echarts.json') // 引入本地json数据文件
      myChart.hideLoading()

      graph.nodes.forEach(function(node) {
        node.label = {
          show: node.symbolSize > 30
        }
      })

      const option = {
        // 添加你的配置
        title: {
          text: 'Les Miserables',
          subtext: 'Default layout',
          top: 'bottom',
          left: 'right'
        },
        tooltip: {},
        legend: [
          {
            // selectedMode: 'single',
            data: graph.categories.map(function(a) {
              return a.name
            })
          }
        ],
        animationDuration: 1500,
        animationEasingUpdate: 'quinticInOut',
        series: [
          {
            name: 'Les Miserables',
            type: 'graph',
            layout: 'none',
            data: graph.nodes,
            links: graph.links,
            categories: graph.categories,
            roam: true,
            label: {
              position: 'right',
              formatter: '{b}'
            },
            lineStyle: {
              color: 'source',
              curveness: 0.3
            },
            emphasis: {
              focus: 'adjacency',
              lineStyle: {
                width: 10
              }
            }
          }
        ]
      }
      myChart.setOption(option)
    })
    return { chartContainer }
  }
}
</script>
