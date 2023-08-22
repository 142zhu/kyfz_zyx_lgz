import request from '@/utils/request'

// 查询奖项信息列表
export function listAward(query) {
  return request({
    url: '/kyfz/award/list',
    method: 'get',
    params: query
  })
}

// 查询奖项信息详细
export function getAward(awardId) {
  return request({
    url: '/kyfz/award/' + awardId,
    method: 'get'
  })
}

// 新增奖项信息
export function addAward(data) {
  return request({
    url: '/kyfz/award',
    method: 'post',
    data: data
  })
}

// 修改奖项信息
export function updateAward(data) {
  return request({
    url: '/kyfz/award',
    method: 'put',
    data: data
  })
}

// 删除奖项信息
export function delAward(awardId) {
  return request({
    url: '/kyfz/award/' + awardId,
    method: 'delete'
  })
}
