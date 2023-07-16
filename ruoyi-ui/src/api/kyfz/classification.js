import request from '@/utils/request'

// 查询行业分类列表
export function listClassification(query) {
  return request({
    url: '/kyfz/classification/list',
    method: 'get',
    params: query
  })
}

// 查询行业分类详细
export function getClassification(categoryId) {
  return request({
    url: '/kyfz/classification/' + categoryId,
    method: 'get'
  })
}

// 新增行业分类
export function addClassification(data) {
  return request({
    url: '/kyfz/classification',
    method: 'post',
    data: data
  })
}

// 修改行业分类
export function updateClassification(data) {
  return request({
    url: '/kyfz/classification',
    method: 'put',
    data: data
  })
}

// 删除行业分类
export function delClassification(categoryId) {
  return request({
    url: '/kyfz/classification/' + categoryId,
    method: 'delete'
  })
}
