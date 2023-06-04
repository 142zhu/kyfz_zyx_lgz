import request from '@/utils/request'

// 查询匹配列表列表
export function listPpxx(query) {
  return request({
    url: '/kyfz/Ppxx/list',
    method: 'get',
    params: query
  })
}

// 查询匹配列表详细
export function getPpxx(id) {
  return request({
    url: '/kyfz/Ppxx/' + id,
    method: 'get'
  })
}

// 新增匹配列表
export function addPpxx(data) {
  return request({
    url: '/kyfz/Ppxx',
    method: 'post',
    data: data
  })
}

// 修改匹配列表
export function updatePpxx(data) {
  return request({
    url: '/kyfz/Ppxx',
    method: 'put',
    data: data
  })
}

// 删除匹配列表
export function delPpxx(id) {
  return request({
    url: '/kyfz/Ppxx/' + id,
    method: 'delete'
  })
}
