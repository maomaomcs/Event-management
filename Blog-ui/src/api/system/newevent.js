import request from '@/utils/request'

// 查询新建赛事列表
export function listNewevent(query) {
  return request({
    url: '/system/newevent/list',
    method: 'get',
    params: query
  })
}

// 查询新建赛事详细
export function getNewevent(id) {
  return request({
    url: '/system/newevent/' + id,
    method: 'get'
  })
}

// 新增新建赛事
export function addNewevent(data) {
  return request({
    url: '/system/newevent',
    method: 'post',
    data: data
  })
}

// 修改新建赛事
export function updateNewevent(data) {
  return request({
    url: '/system/newevent',
    method: 'put',
    data: data
  })
}

// 删除新建赛事
export function delNewevent(id) {
  return request({
    url: '/system/newevent/' + id,
    method: 'delete'
  })
}
